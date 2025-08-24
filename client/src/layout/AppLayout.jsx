import { Outlet } from "react-router-dom";

export const AppLayout = () => {
  return (
    <>
      <header>My Site Header</header>
      <Outlet />
      <footer>My site footer</footer>
    </>
  );
};
