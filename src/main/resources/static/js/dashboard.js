function displayCalorieGoal(userId) {
    const calorieGoal = localStorage.getItem(`Daily_Calorie_Goal_${userId}`);
    if (calorieGoal === null) {
        document.getElementById('calorie-goal').innerHTML = 'You have not set a calorie goal';
    } else {
        document.getElementById('calorie-goal').innerHTML = `Your calorie goal: ${calorieGoal}`;
    }
}
