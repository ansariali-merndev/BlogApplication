import { useState } from "react";
import { CustomInput } from "../components/form/EmailInput";
import { FormHeading } from "../components/form/FormHeading";
import { FormSubmit } from "../components/form/FormSubmit";
import { PasswordInput } from "../components/form/Password";

export const Login = () => {
  const [formdata, setFormdata] = useState({ email: "", password: "" });

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setFormdata((prev) => ({ ...prev, [name]: value }));
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    console.log(formdata);
    setFormdata({ email: "", password: "" });
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
          head={"Welcome to Blog Application"}
          sub={"Keep writing, keep sharing, keep inspiring"}
        />
        <CustomInput
          value={formdata.email}
          onChange={handleOnChange}
          name={"email"}
        />
        <PasswordInput value={formdata.password} onChange={handleOnChange} />
        <FormSubmit isLogin={true} />
      </form>
    </section>
  );
};
