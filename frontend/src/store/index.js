import $ from "jquery";
import { reactive } from "vue";

const food = reactive([]);
const foodJournal = reactive([]);

const userState = reactive({
  person: JSON.parse(sessionStorage.getItem("person")),
});

const methods = {
  // Only checks the username for now, implement a more sophisticated login later.
  async login(username, password) {
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url:
        "http://localhost:8080/api/v1/Person/GetByUsername?username=" +
        username,
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
    foodJournal.value = null
    sessionStorage.removeItem("person");
  },
  async loadFood() {
    await $.ajax({
      url: "http://localhost:8080/api/v1/Food/GetAll",
      method: "get",
    }).done((data) => {
      food.value = data;
    });
  },
  async loadFoodJournal(id) {
    await $.ajax({
      url: "http://localhost:8080/api/v1/FoodEntry/GetByUser?id=" + id,
      method: "get",
    }).done((data) => {
      foodJournal.value = data;
    });
  },
  // Make it take string of id of user and create it in the backend
  async addNewFoodJournalEntry() {
    const entry = {
      creationDate : "",
      totalCarbs: 0,
      totalFat: 0,
      totalProtein: 0,
      userId: userState.person.id,
      foods : [
      ],
    }
    $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/FoodEntry/FoodEntry",
      type: "post",
      data: JSON.stringify(entry),
      success: (data) => {},
      error: (jqXHR) => {
        if (jqXHR.status == 404) userState.person = null;
      },
    });
  },
  async updateFoodJournalEntry(entry) {
    console.log("The entry being updated", entry)
    $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/FoodEntry/Update",
      type: "put",
      data: JSON.stringify(entry),
      success: (data) => {},
      error: (jqXHR) => {
        if (jqXHR.status == 404) userState.person = null;
      },
    });
  },


  async isInFoodJournal(id, date) {
    await $.ajax({
      url:
        "http://localhost:8080/api/v1/FoodEntry/isPresent?id=" +
        id +
        "&date=" +
        date,
      method: "get",
    }).done((data) => {
      console.log(data);
      if (data === false) {
        this.addNewFoodJournalEntry();
      } else {
      }
    });
  },
};

export default {
  userState,
  methods,
  food,
  foodJournal,
};
