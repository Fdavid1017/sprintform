export default function (currency) {
  switch (currency.toLowerCase()) {
    case "huf":
      return "Ft";
    default:
      return "";
  }
}
