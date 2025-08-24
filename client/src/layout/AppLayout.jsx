import { Outlet } from "react-router-dom";
import { Header } from "./Header";
import { Footer } from "./Footer";

export const AppLayout = () => {
  return (
    <>
      <Header />
      <main className="container mx-auto px-4 my-12">
        <Outlet />
      </main>
      <Footer />
    </>
  );
};
