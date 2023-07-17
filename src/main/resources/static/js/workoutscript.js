// Get the date display elements and navigation arrows
const dateDisplay = document.getElementById('dateDisplay');
const prevArrow = document.getElementById('prevArrow');
const nextArrow = document.getElementById('nextArrow');
const workoutList = document.getElementById('workoutList');
const addWorkoutBtn = document.getElementById('addWorkoutBtn');
const modal = document.getElementById('myModal');
const searchBar = document.getElementById('searchBar');
const searchBtn = document.getElementById('searchBtn');
const exerciseOptions = document.getElementById('exerciseOptions');

let currentDate = new Date(); // Variable to store the current date value

// Function to display the current date or "Today"
function displayDate() {
  const today = new Date();
  const options = { month: 'long', day: 'numeric', year: 'numeric' };

  if (currentDate.toDateString() === today.toDateString()) {
    dateDisplay.textContent = 'Today';
  } else {
    const formattedDate = currentDate.toLocaleDateString(undefined, options);
    dateDisplay.textContent = formattedDate;
  }
}

// Function to navigate to the previous date
function goToPreviousDate() {
  currentDate.setDate(currentDate.getDate() - 1);
  displayDate();
}

// Function to navigate to the next date
function goToNextDate() {
  currentDate.setDate(currentDate.getDate() + 1);
  displayDate();
}

function fetchWorkoutsByMuscle(muscle) {
  const apiKey = 'gpZ/zwU7vHvlHtZsnDHqFA==lJ4IKSI51VIxXbb8';
  const url = `https://api.api-ninjas.com/v1/exercises?muscle=${muscle}`;

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
      workoutItem.innerHTML = `<span class="workout-name" id="name"><strong>${workout.name}</strong></span>
                        <span class="workout-type" id="type">${workout.type}</span>
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
  const url = `/workouts/add?name=${workout.name}&type=${workout.type}&difficulty=${workout.difficulty}&uid=${userId}`;

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



// Function to open the modal
function openModal() {
  modal.style.display = 'block';
}

// Function to close the modal
function closeModal() {
  modal.style.display = 'none';
}

// Event listeners for navigation arrows
prevArrow.addEventListener('click', goToPreviousDate);
nextArrow.addEventListener('click', goToNextDate);

// Event listener for add workout button
addWorkoutBtn.addEventListener('click', openModal);

// Event listener for search button
searchBtn.addEventListener('click', () => {
  const muscleSearchInput = searchBar.value.trim();

  if (muscleSearchInput !== '') {
    fetchWorkoutsByMuscle(muscleSearchInput);
  }
});

// Event listener for closing the modal
window.addEventListener('click', (event) => {
  if (event.target === modal) {
    closeModal();
  }
});

// Display the current date initially
displayDate();

// Example usage: Fetch workouts by muscle (based on user input)
// const muscleSearchInput = ''; // Replace with user input
// fetchWorkoutsByMuscle(muscleSearchInput);
