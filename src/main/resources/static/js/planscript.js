const draggables = document.querySelectorAll('.draggable');
const containers = document.querySelectorAll(".food-container");

draggables.forEach(draggable => {
    draggable.addEventListener('dragstart', () => {
      draggable.classList.add('dragging')
    })
  
    draggable.addEventListener('dragend', () => {
      draggable.classList.remove('dragging')
    })
  })
  
  containers.forEach(container => {
    container.addEventListener('dragover', e => {
      e.preventDefault()
      const afterElement = getDragAfterElement(container, e.clientY)
      const draggable = document.querySelector('.dragging')
      if(container.childElementCount < 4 || (container.childElementCount == 4 && draggable.parentElement == container)){
        if (afterElement == null) {
            container.appendChild(draggable)
          } else {
            container.insertBefore(draggable, afterElement)
          }
      }
    })
  })
  
  function getDragAfterElement(container, y) {
    const draggableElements = [...container.querySelectorAll('.draggable:not(.dragging)')]
  
    return draggableElements.reduce((closest, child) => {
      const box = child.getBoundingClientRect()
      const offset = y - box.top - box.height / 2
      if (offset < 0 && offset > closest.offset) {
        return { offset: offset, element: child }
      } else {
        return closest
      }
    }, { offset: Number.NEGATIVE_INFINITY }).element
  }
  

//   document.getElementById("savebtn").addEventListener("click", function(event){
//     var text = document.getElementById("inputtitle").value;
//     document.getElementById("planTitle").value = text;
//     event.preventDefault();
//     return false;
// });

/*get and add data*/
function openNutritionData(modalId, plansId){
  document.getElementById(modalId).style.display = "block"
  document.getElementById("planId").value = plansId;
  console.log(plansId);
  // document.getElementById("page-info-container").style.display = "none"
  // document.getElementById("vertical-navigation").style.display = "none"
}

function closeNutritionData(modalId){
  document.getElementById(modalId).style.display = "none"
  document.getElementById("page-info-container").style.display = "block"
  document.getElementById("vertical-navigation").style.display = "block"
}
