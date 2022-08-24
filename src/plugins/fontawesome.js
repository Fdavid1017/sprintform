import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faDatabase,
  faHouse,
  faCarSide,
  faBurger,
  faCircleQuestion,
  faFile,
  faStethoscope,
  faCoins,
  faPersonRunning,
  faGamepad,
  faFolder,
} from "@fortawesome/free-solid-svg-icons";

export default function setup() {
  library.add(
    faDatabase,
    faHouse,
    faCarSide,
    faBurger,
    faCircleQuestion,
    faFile,
    faStethoscope,
    faCoins,
    faPersonRunning,
    faGamepad,
    faFolder
  );
}
