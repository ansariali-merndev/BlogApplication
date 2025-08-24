export const Footer = () => {
  return (
    <footer
      className="h-[10vh] bg-gray-100 text-xs italic text-blue-600 flex flex-col justify-center items-center"
      style={{
        boxShadow:
          "rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 2px 6px 2px",
      }}
    >
      <p>Thanks for using Blog Application. Share it with your friends!</p>
      <p>
        Made with ❤️ by <span className="font-semibold">Ansari Ali</span>
      </p>
    </footer>
  );
};
