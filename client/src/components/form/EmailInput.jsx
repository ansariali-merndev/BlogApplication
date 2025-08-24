import { MdEmail } from "react-icons/md";

export const CustomInput = ({ value, onChange, name, placeholder }) => {
  return (
    <div className="flex items-center border rounded-md px-3 py-2 bg-gray-100">
      <MdEmail className="text-gray-600 mr-2" size={20} />
      <input
        type="text"
        name={name}
        placeholder={placeholder || "eg: you@gmail.com"}
        required
        value={value}
        onChange={onChange}
        autoComplete="off"
        className="bg-transparent outline-none w-64 text-sm text-gray-700"
      />
    </div>
  );
};
