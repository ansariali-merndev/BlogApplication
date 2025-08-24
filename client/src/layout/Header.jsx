import { NavLink, useNavigate } from "react-router-dom";
import { useUser } from "../utils/UserContext";
import { useEffect, useState } from "react";
import profile from "../assets/profile.jpg";
import { getRequest } from "../axios";

export const Header = () => {
  const [isAuthorized, setIsAuthorized] = useState(false);
  const [openMenubox, setOpenMenubox] = useState(false);
  const { userDetail } = useUser();
  const navigate = useNavigate();

  useEffect(() => {
    setIsAuthorized(userDetail.isAuthorized);
  }, [userDetail]);

  const logoutFunc = async () => {
    setOpenMenubox(false);
    const res = await getRequest("/auth/logout");
    console.log(res);
    navigate("/login");
  };

  return (
    <header
      className="flex justify-between items-center px-2 sm:px-4 md:px-12 h-[10vh] bg-gray-100"
      style={{ boxShadow: "rgba(100, 100, 111, 0.2) 0px 7px 29px 0px" }}
    >
      <div className="font-bold text-neutral-600 md:text-lg tracking-wide py-1">
        Blog Platform
      </div>
      {isAuthorized ? (
        <div className="relative">
          <img
            src={profile}
            onClick={() => setOpenMenubox((prev) => !prev)}
            alt="profile"
            className="w-10 h-auto rounded-full cursor-pointer border border-neutral-600"
          />
          <div
            className={`pr-4 pl-4 py-4 z-30 w-56 absolute -left-50 bg-indigo-600 rounded-xl transition-all duration-200 ease-in-out ${
              openMenubox ? "opacity-100 visible" : "opacity-0 invisible"
            }`}
            style={{
              boxShadow: "rgba(0, 0, 0, 0.24) 0px 3px 8px",
            }}
          >
            <p
              onClick={() => setOpenMenubox(false)}
              className="px-3 py-2 rounded-md text-white hover:bg-indigo-500 cursor-pointer transition-colors"
            >
              My Post
            </p>
            <p
              onClick={() => setOpenMenubox(false)}
              className="px-3 py-2 rounded-md text-white hover:bg-indigo-500 cursor-pointer transition-colors"
            >
              New Post
            </p>
            <p
              onClick={logoutFunc}
              className="px-3 py-2 rounded-md text-red-200 hover:bg-red-400 cursor-pointer transition-colors"
            >
              Logout
            </p>
          </div>
        </div>
      ) : (
        <NavLink
          to={"/login"}
          className="bg-sky-200 px-4 py-1 transition-colors duration-200 ease-in-out rounded-md cursor-pointer text-neutral-900 font-semibold hover:bg-purple-500"
        >
          Login
        </NavLink>
      )}
    </header>
  );
};
