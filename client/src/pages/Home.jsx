import { useEffect } from "react";
import { useUser } from "../utils/UserContext";
import { getRequest } from "../axios";

export const Home = () => {
  const { setUserDetail } = useUser();

  async function getProfile() {
    try {
      const res = await getRequest("/auth/profile");
      if (res.status === 200 && res.data?.success === true) {
        setUserDetail({
          isAuthorized: true,
          userId: res.data?.id,
          userEmail: res.data?.email,
        });
      } else {
        window.location.href = "/login";
      }
    } catch (error) {
      console.log("Error: ", error);
      window.location.href = "/login";
    }
  }

  useEffect(() => {
    getProfile();
  }, []);

  return (
    <section>
      <h2>Hello world</h2>
    </section>
  );
};
