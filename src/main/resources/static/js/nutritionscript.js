//date id
const currDate = Date.now()
const dt = new Date(currDate)
document.getElementById("nutrition-date").innerHTML = dt.toDateString()

function openBreakfastData(){
    document.getElementById("breakfast-modal").style.display = "block"
}

function closeBreakfastData(){
    document.getElementById("breakfast-modal").style.display = "none"
}


function openLunchData(){
    document.getElementById("lunch-modal").style.display = "block"
}

function closeLunchData(){
    document.getElementById("lunch-modal").style.display = "none"
}

function openDinnerData(){
    document.getElementById("dinner-modal").style.display = "block"
}

function closeDinnerData(){
    document.getElementById("dinner-modal").style.display = "none"
}

function openSnackData(){
    document.getElementById("snack-modal").style.display = "block"
}

function closeSnackData(){
    document.getElementById("snack-modal").style.display = "none"
}
