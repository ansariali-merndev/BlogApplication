import { NavLink } from "react-router-dom";

export const Header = () => {
  return (
    <header
      className="flex justify-between items-center px-2 sm:px-4 md:px-12 h-[10vh] bg-gray-100"
      style={{ boxShadow: "rgba(100, 100, 111, 0.2) 0px 7px 29px 0px" }}
    >
      <div className="font-bold text-neutral-600 md:text-lg tracking-wide py-1">
        Blog Platform
      </div>
      <NavLink
        to={"/login"}
        className="bg-sky-200 px-4 py-1 transition-colors duration-200 ease-in-out rounded-md cursor-pointer text-neutral-900 font-semibold hover:bg-purple-500"
      >
        Login
      </NavLink>
    </header>
  );
};
