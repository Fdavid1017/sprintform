import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";

export default function (category) {
  switch (category) {
    case TransactionCategoryEnum.HOUSING:
      return "#9e0142";
    case TransactionCategoryEnum.TRAVEL:
      return "#d53e4f";
    case TransactionCategoryEnum.FOOD:
      return "#f46d43";
    case TransactionCategoryEnum.UTILITIES:
      return "#fdae61";
    case TransactionCategoryEnum.INSURANCE:
      return "#fee08b";
    case TransactionCategoryEnum.HEALTHCARE:
      return "#fee08b";
    case TransactionCategoryEnum.FINANCIAL:
      return "#abdda4";
    case TransactionCategoryEnum.LIFESTYLE:
      return "#66c2a5";
    case TransactionCategoryEnum.ENTERTAINMENT:
      return "#3288bd";
    case TransactionCategoryEnum.MISCELLANEOUS:
      return "#5e4fa2";
    default:
      return "black";
  }
}
