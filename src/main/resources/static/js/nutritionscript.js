//date id
const currDate = Date.now()
const dt = new Date(currDate)
document.getElementById("nutrition-date").innerHTML = dt.toDateString()

/*get and add data*/
function openNutritionData(modalId){
    document.getElementById(modalId).style.display = "block"
}

function closeNutritionData(modalId){
    document.getElementById(modalId).style.display = "none"
}

