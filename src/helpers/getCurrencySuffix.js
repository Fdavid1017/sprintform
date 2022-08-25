import CurrencyEnum from "@/enums/CurrencyEnum";

export default function (currency) {
  switch (currency) {
    case "HUF":
      return CurrencyEnum.HUF;
    case "EUR":
      return CurrencyEnum.EUR;
    case "USD":
      return CurrencyEnum.USD;
    default:
      return "";
  }
}
