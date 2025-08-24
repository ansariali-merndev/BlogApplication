import { useState } from "react";
import z from "zod";
import { CustomInput } from "../components/form/EmailInput";
import { FormHeading } from "../components/form/FormHeading";
import { FormSubmit } from "../components/form/FormSubmit";
import { PasswordInput } from "../components/form/Password";
import { handleWarnSwal } from "../utils/swal";
import { postRequest } from "../axios";
import { useUser } from "../utils/UserContext";
import { useNavigate } from "react-router-dom";

const formSchema = z.object({
  firstName: z
    .string()
    .min(3, { message: "First name must be at least 3 characters long" })
    .max(12, { message: "First name cannot exceed 12 characters" }),
  lastName: z
    .string()
    .min(3, { message: "Last name must be at least 3 characters long" })
    .max(12, { message: "Last name cannot exceed 12 characters" }),
  email: z.email({ message: "Please enter a valid email address" }),
  password: z
    .string()
    .min(8, { message: "Password must be at least 8 characters long" })
    .max(15, { message: "Password cannot exceed 15 characters" })
    .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/, {
      message:
        "Password must contain at least one uppercase letter, one lowercase letter, and one number",
    }),
});

export const Register = () => {
  const { setUserDetail } = useUser();
  const navigate = useNavigate();
  const [isPending, setIsPending] = useState(false);
  const [formdata, setFormdata] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });

  const handleOnchange = (e) => {
    const { name, value } = e.target;
    setFormdata((prev) => ({ ...prev, [name]: value }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    setIsPending(true);
    const { success, error, data } = formSchema.safeParse(formdata);
    if (!success) {
      handleWarnSwal(error.issues[0].message);
      setIsPending(false);
      return;
    }
    const res = await postRequest("/auth/register", data);
    if (res.status === 200 && res.data.success === true) {
      setUserDetail({
        isAuthorized: true,
        userId: res?.data?.id,
        userEmail: res?.data?.userEmail,
      });
    } else {
      handleWarnSwal("Something went wrong, Please try later!");
    }
    setIsPending(false);
    setFormdata({
      firstName: "",
      lastName: "",
      email: "",
      password: "",
    });

    if (res?.data?.success) {
      navigate("/");
    }
  };

  return (
    <section className="grid items-center justify-center">
      <form
        onSubmit={handleFormSubmit}
        className="border border-rose-300 p-12 rounded-md space-y-6"
        style={{
          boxShadow:
            "rgba(0, 0, 0, 0.2) 0px 12px 28px 0px, rgba(0, 0, 0, 0.1) 0px 2px 4px 0px, rgba(255, 255, 255, 0.05) 0px 0px 0px 1px inset",
        }}
      >
        <FormHeading
          head={"Get Started with Blog Application"}
          sub={"Join thousands of users already enjoying our platform"}
        />

        <CustomInput
          name={"firstName"}
          placeholder={"First name (e.g., David)"}
          value={formdata.firstName}
          onChange={handleOnchange}
        />
        <CustomInput
          name={"lastName"}
          placeholder={"Last name (e.g., Miller)"}
          value={formdata.lastName}
          onChange={handleOnchange}
        />
        <CustomInput
          name={"email"}
          value={formdata.email}
          onChange={handleOnchange}
        />
        <PasswordInput value={formdata.password} onChange={handleOnchange} />
        <FormSubmit isLogin={false} isPending={isPending} />
      </form>
    </section>
  );
};
