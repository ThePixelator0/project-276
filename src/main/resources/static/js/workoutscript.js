// Get the date display elements and navigation arrows
const dateDisplay = document.getElementById('dateDisplay');
const prevArrow = document.getElementById('prevArrow');
const nextArrow = document.getElementById('nextArrow');
const workoutList = document.getElementById('workoutList');
const addWorkoutBtn = document.getElementById('addWorkoutBtn');
const modal = document.getElementById('myModal');
const modal2 = document.getElementById('myModal2');
const searchBar = document.getElementById('searchBar');
const searchBtn = document.getElementById('searchBtn');
const searchBar2 = document.getElementById('setWorkout');
const searchBtn2 = document.getElementById('setBtn');
const exerciseOptions = document.getElementById('exerciseOptions');
const workoutsGoal = document.getElementById('workoutGoal');
const workoutsCount = document.getElementById('remainingCount');

let currentDate = new Date(); // Variable to store the current date value

// Function to display the current date or "Today"
function displayDate() {
  const today = new Date();
  const options = { month: 'long', day: 'numeric', year: 'numeric' };

  if (currentDate.toDateString() === today.toDateString()) {
    dateDisplay.textContent = 'Today';
    addWorkoutBtn.style.display = 'inline';
    workoutsGoal.style.display = 'inline';
  } else {
    workoutsGoal.style.display = 'none';
    addWorkoutBtn.style.display = 'none';
    const formattedDate = currentDate.toLocaleDateString(undefined, options);
    dateDisplay.textContent = formattedDate;
  }
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, '0');
  const day = String(currentDate.getDate()).padStart(2, '0');
  const localDate = `${year}-${month}-${day}`;



  filterWorkoutsByDate(localDate);
}

// Function to navigate to the previous date
function goToPreviousDate() {
  currentDate.setDate(currentDate.getDate() - 1);
  displayDate();
  // filterWorkoutsByDate(currentDate.toISOString().split('T')[0]);
}

// Function to navigate to the next date
function goToNextDate() {
  currentDate.setDate(currentDate.getDate() + 1);
  displayDate();
  // filterWorkoutsByDate(currentDate.toISOString().split('T')[0]);
}

function fetchWorkoutsByMuscle(muscle) {
  const apiKey = 'gpZ/zwU7vHvlHtZsnDHqFA==lJ4IKSI51VIxXbb8';
  const url = `https://api.api-ninjas.com/v1/exercises?name=${muscle}`;

  // Make API request
  fetch(url, {
    method: 'GET',
    headers: {
      'X-Api-Key': apiKey
    },
    contentType: 'application/json'
  })
  .then(response => response.json())
  .then(result => {
    // Process the API response and display workouts
    console.log(result);
    // Update workoutList with the retrieved workout data
    exerciseOptions.innerHTML = ''; // Clear previous workout items

    result.forEach(workout => {
      const workoutItem = document.createElement('div');
      workoutItem.className = 'workout-item';
      workoutItem.innerHTML = `<span class="workout-name" id="name"><strong>${workout.name}</strong></span><br>
                        <span class="workout-type" id="type">${workout.type}</span><br>
                        <span class="workout-difficulty" id="difficulty"><em>${workout.difficulty}</em></span>`;

      // Add a click event listener to each workout item
      workoutItem.addEventListener('click', () => {
        const userId = document.getElementById('userId').textContent;
        addWorkoutToBackend(workout, userId);
      });

      exerciseOptions.appendChild(workoutItem);
    });
  })
  .catch(error => {
    console.error('Error: ', error);
  });
}

function addWorkoutToBackend(workout, userId) {
  const url = `/workouts/add?name=${workout.name}&type=${workout.type}&difficulty=${workout.difficulty}&userid=${userId}`;

  fetch(url, {
    method: 'POST'
  })
  .then(response => {
    if (response.ok) {
      console.log('Workout added to backend');
      location.reload(); // Refresh the page
    } else {
      console.error('Failed to add workout to backend');
    }
  })
  .catch(error => {
    console.error('Error adding workout to backend:', error);
  });
}

// function deleteWorkout(workoutId) {
//   console.log(workoutId);
//   if (confirm('Are you sure you want to remove this workout?')) {
//       $.ajax({
//           type: 'POST',
//           url: '/workouts/' + workoutId + '/delete',
//           success: function () {
//               // Reload the page or perform any other action after successful deletion
//               location.reload();
//           },
//           error: function (xhr, status, error) {
//               // Handle the error, display a message, or perform any other action
//               console.log(error);
//           }
//       });
//   }
// }
let scounts = 0;
function filterWorkoutsByDate(date) {
  const workoutItems = document.querySelectorAll('.workouts-added');
  let count = 0;

  workoutItems.forEach((workoutItem) => {
    const workoutDateElement = workoutItem.querySelector('#workoutDate');
    const workoutDate = workoutDateElement.textContent;
    
    if (workoutDate === date) {
      workoutItem.style.display = 'block';
      count++;
      scounts++;
    } else {
      workoutItem.style.display = 'none';
    }

    localStorage.setItem('workoutCount', count);
    workoutsCount.textContent = `Total workout count: ${count}`;
  });
}

function setWorkoutGoal() {

}


// Function to open the modal
function openModal() {
  modal.style.display = 'block';
}

// Function to close the modal
function closeModal() {
  modal.style.display = 'none';
}

function openModal2() {
  modal2.style.display = 'block';
}
function closeModal2() {
  modal2.style.display = 'none';
}

// Event listeners for navigation arrows
prevArrow.addEventListener('click', goToPreviousDate);
nextArrow.addEventListener('click', goToNextDate);

// Event listener for add workout button
addWorkoutBtn.addEventListener('click', openModal);

workoutGoalBtn.addEventListener('click', openModal2);

// Event listener for search button
searchBtn.addEventListener('click', () => {
  const muscleSearchInput = searchBar.value.trim();

  if (muscleSearchInput !== '') {
    fetchWorkoutsByMuscle(muscleSearchInput);
  }
});

// Function to save the value in local storage
const saveWorkoutGoalToLocalStorage = (workoutGoal) => {
  localStorage.setItem('workoutGoal', workoutGoal);
};

// Function to retrieve the value from local storage
const getWorkoutGoalFromLocalStorage = () => {
  return localStorage.getItem('workoutGoal');
};

// Check if there is a saved value in local storage and update the workoutsGoal element
const savedWorkoutGoal = getWorkoutGoalFromLocalStorage();
if (savedWorkoutGoal) {
  workoutGoalBtn.style.display = 'none';
  // let goals = 0;
  // goals += parseInt(savedWorkoutGoal);
  // const finalnum = goals - scounts;
  // displayDate()
  // workoutsGoal.textContent = `You have ${finalnum} remaining workouts`;
}

searchBtn2.addEventListener('click', () => {
  const workoutGoal = searchBar2.value.trim();

  if (workoutGoal !== '') {
    // Update the workoutsGoal element
    // workoutsGoal.textContent = `You have ${scounts} remaining workouts`;
    displayDate()
    let goals = 0;
    goals += parseInt(workoutGoal, 10);
    let finalnum = (goals - scounts);
    location.reload();
    workoutsGoal.textContent = `You have ${finalnum} remaining workouts to reach your goal!`;

    // Save the value in local storage
    saveWorkoutGoalToLocalStorage(workoutGoal);
    closeModal2();
  }
});

if (savedWorkoutGoal) {
  displayDate()
  let goals = 0;
  goals += parseInt(getWorkoutGoalFromLocalStorage(), 10);
  let finalnum = (goals - scounts);
  workoutsGoal.textContent = `You have ${finalnum} remaining workouts to reach your goal!`;
  console.log("hello");
}

// Event listener for closing the modal
window.addEventListener('click', (event) => {
  if (event.target === modal) {
    closeModal();
  }
});

window.addEventListener('click', (event) => {
  if (event.target === modal2) {
    closeModal2();
  }
});

// Display the current date initially
displayDate();

// Example usage: Fetch workouts by muscle (based on user input)
// const muscleSearchInput = ''; // Replace with user input
// fetchWorkoutsByMuscle(muscleSearchInput);
