const apiParams = {
    apiKey: 'e1Qeo8EhfrLVg7E8hvBXp4eOYhnaOudpgeXLkGml',
    dataType: ["Survey (FNDDS)"],
    pageSize: 3
}


/*get and add data*/
function openNutritionData(modalId){
    document.getElementById(modalId).style.display = "block"
    document.getElementById("page-info-container").style.display = "none"
    document.getElementById("vertical-navigation").style.display = "none"
}

function closeNutritionData(modalId){
    document.getElementById(modalId).style.display = "none"
    document.getElementById("page-info-container").style.display = "block"
    document.getElementById("vertical-navigation").style.display = "block"
}



//api post request
function searchFoods(foodInput, dataListId, foodCodeId){
    const apiUrl = `https://api.nal.usda.gov/fdc/v1/foods/search?api_key=${apiParams.apiKey}&query=${foodInput}&dataType=${apiParams.dataType}`
    
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            var datalist = document.getElementById(dataListId);
            datalist.innerHTML = '';
            
            var foods = data.foods
            foods.forEach(element => {
                const option = document.createElement('option')
                option.value = element.description
                option.id = element.fdcId
                datalist.appendChild(option)
            });

            var options = datalist.childNodes;
            var foodCode = document.getElementById(foodCodeId)

            for(var i=0; i<options.length; i++){
                if(options[i].value === foodInput){
                    console.log("found")
                    foodCode.value = options[i].id
                }
            }
            
        })
}

function onSubmitForm(foodCodeId){
    var foodCode = document.getElementById(foodCodeId)
    if(foodCode.value === ""){
        alert("This food was not found in the database. Please make sure you select a food from the drop down list")
        return false
    }
    
    return true
}

function calorieGoal(event){
    event.preventDefault();
    var form = document.getElementById('set-calorie-form')
    
    localStorage.setItem(`Daily_Calorie_Goal_${userId}`, form.elements["set-calorie-goal"].value)

    document.getElementById('calorie-goal').innerHTML = form.elements["set-calorie-goal"].value

    document.getElementById('calorie-modal').style.display = 'none';
    document.getElementById("page-info-container").style.display = "block"
    document.getElementById("vertical-navigation").style.display = "block"
    
}

document.addEventListener('DOMContentLoaded', () => {
    console.log(localStorage.getItem(`Daily_Calorie_Goal_${userId}`))
    if(localStorage.getItem(`Daily_Calorie_Goal_${userId}`) === null){
        document.getElementById('calorie-goal').innerHTML = 'You have not set a calorie goal'
    } else {
        document.getElementById('calorie-goal').innerHTML = localStorage.getItem(`Daily_Calorie_Goal_${userId}`)
    }
})

function calorieCalculation(userId){
    //make an ajax request
    console.log("Calorie Calculation")
    const apiUrl = `/api/nutrition/totalCalorieCount/${userId}`
    var returnValue;
    $.ajax({
        url: apiUrl,
        type: 'GET',
        success: function(data){

            if(document.getElementById('calorie-goal').innerHTML !== 'You have not set a calorie goal'){
                returnValue = Number(document.getElementById('calorie-goal').innerHTML) - Number(data);

                document.getElementById('calorie-goal').innerHTML = String(returnValue) 
            }
        }
    })

}

//ajax request to get info
function getNutritionInfo(mealType, userId, modalId, modalData){
    var modal = document.getElementById(modalId)
    const apiUrl = `/api/nutrition/allNutrition/${mealType}/${userId}`
    $.ajax({
        url: apiUrl,
        type: 'GET',
        success: function(data){
            var nutrientData = document.getElementById(modalData)
            nutrientData.innerHTML = ""

            for(var i=0; i<data.length; i++){
                var creationTime = new Date(data[i].creationDateTime)
                nutrientData.innerHTML += `<p>${data[i].id}</p>`

                nutrientData.innerHTML += `<p>${creationTime.toDateString()}</p>`

                nutrientData.innerHTML += `<p>${data[i].mealType}</p>`

                nutrientData.innerHTML += `<p>${data[i].product}</p>`
        

                nutrientData.innerHTML += `<p>${data[i].protein}</p>`

                nutrientData.innerHTML += `<p>${data[i].calorie}</p>`

                nutrientData.innerHTML += `<p>${data[i].fat}</p>`

                nutrientData.innerHTML += `<p><a href="/api/nutrition/deleteNutrition/${data[i].id}">Remove</a></p>`
            }
        },
        error: function(error){
            console.log("Error: ",error)
        }
    })
    document.getElementById("page-info-container").style.display = "none"
    document.getElementById("vertical-navigation").style.display = "none"
    modal.style.display = "block"
}


function getPastData(timeInterval, userId){
    const apiUrl = `/api/nutrition/${timeInterval}/${userId}`
    $.ajax({
        url: apiUrl,
        type: 'GET',
        success: function(data){
            console.log(data);
            var totalFat = 0;
            var totalProtein = 0;
            var totalCalorie = 0;
            for(var i=0; i<data.length; i++){
                totalFat += Number(data[i].fat);
                totalProtein += Number(data[i].protein);
                totalCalorie += Number(data[i].calorie);
            }

            document.getElementById(`${timeInterval}-fat-intake`).innerHTML = totalFat.toString()
            
            document.getElementById(`${timeInterval}-calorie-intake`).innerHTML = totalCalorie.toString()
            
            document.getElementById(`${timeInterval}-protein-intake`).innerHTML = totalProtein.toString();
        },
        error: function(error){
            console.log("Error: ", error)
        }
    })
    document.getElementById(timeInterval).style.display = "block";
    document.getElementById("page-info-container").style.display = "none"
    document.getElementById("vertical-navigation").style.display = "none"
}