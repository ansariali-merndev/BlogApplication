import axios from "axios";

const instance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  withCredentials: true,
});

export const getRequest = async (uri) => {
  return await instance.get(uri);
};

export const postRequest = async (uri, data) => {
  return await instance.post(uri, data);
};
