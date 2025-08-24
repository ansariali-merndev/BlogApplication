import { NavLink } from "react-router-dom";

export const FormSubmit = ({ isLogin, isPending }) => {
  return (
    <div>
      <button
        disabled={isPending}
        className="bg-blue-600 w-full py-1 transition-colors duration-300 text-zinc-100 rounded-md cursor-pointer hover:bg-purple-900 disabled:cursor-not-allowed disabled:bg-sky-500"
        type="submit"
      >
        Submit
      </button>
      <p className="text-center text-sm font-semibold">
        {isLogin ? "Don't " : "Already "} have an account{" "}
        <NavLink
          to={isLogin ? "/register" : "/login"}
          className="cursor-pointer text-indigo-600"
        >
          {isLogin ? "Create Account" : "Sign In"}
        </NavLink>
      </p>
    </div>
  );
};
