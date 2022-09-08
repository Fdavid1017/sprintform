import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";

export default function (category) {
  switch (category) {
    case TransactionCategoryEnum.housing:
      return "#9e0142";
    case TransactionCategoryEnum.travel:
      return "#d53e4f";
    case TransactionCategoryEnum.food:
      return "#f46d43";
    case TransactionCategoryEnum.utilities:
      return "#fdae61";
    case TransactionCategoryEnum.insurance:
      return "#fee08b";
    case TransactionCategoryEnum.healthcare:
      return "#fee08b";
    case TransactionCategoryEnum.financial:
      return "#abdda4";
    case TransactionCategoryEnum.lifestyle:
      return "#66c2a5";
    case TransactionCategoryEnum.entertainment:
      return "#3288bd";
    case TransactionCategoryEnum.miscellaneous:
      return "#5e4fa2";
    default:
      return "black";
  }
}
