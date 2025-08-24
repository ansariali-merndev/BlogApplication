export const FormHeading = ({ head, sub }) => {
  return (
    <div className="text-center">
      <h2 className="font-bold text-xl text-blue-600">{head}</h2>
      <p className="text-gray-600">{sub}</p>
    </div>
  );
};
