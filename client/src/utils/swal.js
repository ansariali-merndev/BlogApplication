import Swal from "sweetalert2";

export const handleWarnSwal = (message) => {
  Swal.fire({
    icon: "warning",
    text: message || "Oops! Something went wrong",
  });
};
