export default class FoodJournal {
constructor() {
    this.creationDate = "",
    this.foods = [
        (FoodEntryData = {
        servingSize: "",
        Food: {
            name: "",
            carbs: 0,
            fat: 0,
            protein: 0,
            },
        }),
    ],
    
    this.totalCarbs = 0,
    this.totalFat = 0,
    this.totalProtein = 0,
    this.userId = store.user.state.id;
    }
}
