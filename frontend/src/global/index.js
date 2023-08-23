import $ from "jquery";
import { reactive } from "vue";

const userState = reactive({
  person: JSON.parse(sessionStorage.getItem("person")),
});

const methods = {
  async login(person) {
    await $.ajax({
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/Person/Login",
      type: "post",
      
      data: JSON.stringify(person),
      success: (data) => {
        userState.person = data;
        sessionStorage.setItem("person", JSON.stringify(userState.person));
      },
      error: (jqXHR) => {
        if (jqXHR.status == 404) userState.person = null;
      },
    });
  },
  logout() {
    userState.person = null;
    sessionStorage.removeItem("person");
  }
};

export default {
  userState,
  methods
}
