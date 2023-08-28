import $ from "jquery";
import { reactive } from "vue";

const food = reactive([]);

const userState = reactive({
  person: JSON.parse(sessionStorage.getItem("person")),
});

const methods = {
  // Only check the username for now, implement a more sophisticated login later.
  async login(username, password) {
    await $.ajax({
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/Person/GetByUsername?username=" + username,
      type: "get",
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
  },
  async loadFood() {
    await $.ajax({
      url: "http://localhost:8080/api/v1/Food/GetAll",
      method: "get"
    }).done(data => {
      food.value = data;
    })
  }
};

export default {
  userState,
  methods,
  food
}
