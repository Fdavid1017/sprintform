import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import fontawesome from "@/plugins/fontawesome";

fontawesome();

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(router)
  .mount("#app");
