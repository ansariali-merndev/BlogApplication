import { useState } from "react";
import { CustomInput } from "../components/form/EmailInput";
import { FormHeading } from "../components/form/FormHeading";
import { FormSubmit } from "../components/form/FormSubmit";
import { PasswordInput } from "../components/form/Password";

export const Register = () => {
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

  const handleFormSubmit = (e) => {
    e.preventDefault();
    console.log(formdata);
    setFormdata({
      firstName: "",
      lastName: "",
      email: "",
      password: "",
    });
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
        <FormSubmit isLogin={false} />
      </form>
    </section>
  );
};
