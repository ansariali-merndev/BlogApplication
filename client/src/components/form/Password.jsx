import { useState } from "react";
import { MdLock } from "react-icons/md";
import { FaEye, FaEyeSlash } from "react-icons/fa";

export const PasswordInput = ({ value, onChange }) => {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <div className="flex items-center border rounded-md px-3 py-2 bg-gray-100">
      <MdLock className="text-gray-600 mr-2" size={20} />
      <input
        type={showPassword ? "text" : "password"}
        name="password"
        placeholder="Minimum 8 characters required"
        required
        value={value}
        onChange={onChange}
        autoComplete="off"
        className="bg-transparent outline-none w-full text-sm text-gray-700"
      />
      <div
        className="w-fit cursor-pointer"
        onClick={() => setShowPassword((prev) => !prev)}
      >
        {showPassword ? <FaEye /> : <FaEyeSlash />}
      </div>
    </div>
  );
};
