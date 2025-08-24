import { createContext, useContext, useEffect, useState } from "react";

const UserContext = createContext();

const initialUserDetail = {
  isAuthorized: false,
  userId: "",
  userEmail: "",
};

export const UserProvider = ({ children }) => {
  const [userDetail, setUserDetail] = useState(initialUserDetail);
  const value = { userDetail, setUserDetail };

  useEffect(() => {
    console.log(userDetail);
  }, [userDetail]);

  return <UserContext.Provider value={value}>{children}</UserContext.Provider>;
};

export const useUser = () => {
  return useContext(UserContext);
};
