console.log("Welcome to the Community Portal");

window.onload = function(){

    alert("Page Loaded Successfully!");

};

function showMessage(){

    document.getElementById("confirmationMessage").innerHTML =
    "Registration Successful!";

}

function validatePhone(){

    let phone =
    document.getElementById("phone").value;

    if(phone.length != 10){

        alert("Phone number must be 10 digits");

    }

}

function videoReady(){

    document.getElementById("videoMessage").innerHTML =
    "Video ready to play";

}   
const eventName = "Music Festival";

const eventDate = "15 July 2026";

let seatsAvailable = 50;

console.log(
`Event Name: ${eventName}
Event Date: ${eventDate}
Seats Available: ${seatsAvailable}`
);

seatsAvailable--;

console.log(
"Remaining Seats:",
seatsAvailable
);

seatsAvailable++;

console.log(
"Updated Seats:",
seatsAvailable
);
const events = [

    {
        name: "Music Festival",
        seats: 20
    },

    {
        name: "Food Carnival",
        seats: 0
    },

    {
        name: "Sports Competition",
        seats: 15
    }

];
events.forEach(function(event){

    if(event.seats > 0){

        console.log(
            event.name + " is Available"
        );

    }
    else{

        console.log(
            event.name + " is Full"
        );

    }

});
function registerUser(event){

    try{

        if(event.seats <= 0){

            throw new Error(
                "No Seats Available"
            );

        }

        event.seats--;

        console.log(
            "Successfully Registered for " +
            event.name
        );

    }

    catch(error){

        console.log(
            error.message
        );

    }

}
registerUser(events[0]);

registerUser(events[1]);
const communityEvents = [

    {
        name: "Music Festival",
        category: "Music",
        seats: 20
    },

    {
        name: "Food Carnival",
        category: "Food",
        seats: 15
    }

];

function addEvent(name, category, seats){

    communityEvents.push({

        name,
        category,
        seats

    });

}

function registerUser(eventName){

    const event =

    communityEvents.find(

        e => e.name === eventName

    );

    if(event){

        event.seats--;

        console.log(
            "Registered for " +
            event.name
        );

    }

}

function filterEventsByCategory(category){

    return communityEvents.filter(

        event =>
        event.category === category

    );

}

function registrationTracker(){

    let totalRegistrations = 0;

    return function(){

        totalRegistrations++;

        console.log(
            "Total Registrations: " +
            totalRegistrations
        );

    };

}

const musicCounter =
registrationTracker();

musicCounter();
musicCounter();
musicCounter();
function searchEvents(callback){

    return callback(
        communityEvents
    );

}

const result =
searchEvents(function(events){

    return events.filter(

        event =>
        event.seats > 10

    );

});
class Event {

    constructor(name, category, seats) {

        this.name = name;
        this.category = category;
        this.seats = seats;

    }

}

Event.prototype.checkAvailability = function() {

    return this.seats > 0;

};

const musicEvent = new Event(
    "Music Festival",
    "Music",
    50
);

const foodEvent = new Event(
    "Food Carnival",
    "Food",
    25
);

const fullEvent = new Event(
    "Dance Show",
    "Dance",
    0
);

console.log(
    musicEvent.checkAvailability()
);

console.log(
    fullEvent.checkAvailability()
);

Object.entries(musicEvent)
.forEach(function([key, value]) {

    console.log(
        key + " : " + value
    );

});
const eventsList = [

    {
        name: "Music Festival",
        category: "Music"
    },

    {
        name: "Food Carnival",
        category: "Food"
    },

    {
        name: "Sports Competition",
        category: "Sports"
    }

];

eventsList.push({

    name: "Dance Show",
    category: "Dance"

});

eventsList.push({

    name: "Rock Concert",
    category: "Music"

});

console.log(eventsList);

const musicEvents =

eventsList.filter(

    event => event.category === "Music"

);

console.log(musicEvents);

const eventCards =

eventsList.map(

    event =>

    `Workshop on ${event.name}`

);

eventCards.forEach(

    card => console.log(card)

);
const communityEvents = [

    {
        name: "Music Festival",
        category: "Music",
        seats: 20
    },

    {
        name: "Food Carnival",
        category: "Food",
        seats: 15
    },

    {
        name: "Sports Competition",
        category: "Sports",
        seats: 10
    }

];

const container =
document.querySelector("#eventContainer");

communityEvents.forEach(function(event){

    const card =
    document.createElement("div");

    card.classList.add("eventBox");

    const title =
    document.createElement("h3");

    title.textContent =
    event.name;

    const category =
    document.createElement("p");

    category.textContent =
    `Category: ${event.category}`;

    const seatText =
    document.createElement("p");

    seatText.textContent =
    `Seats: ${event.seats}`;

    const registerBtn =
    document.createElement("button");

    registerBtn.textContent =
    "Register";

    registerBtn.onclick = function(){

        if(event.seats > 0){

            event.seats--;

            seatText.textContent =
            `Seats: ${event.seats}`;

        }
        else{

            alert("No Seats Available");

        }

    };

    const cancelBtn =
    document.createElement("button");

    cancelBtn.textContent =
    "Cancel";

    cancelBtn.onclick = function(){

        event.seats++;

        seatText.textContent =
        `Seats: ${event.seats}`;

    };

    card.appendChild(title);

    card.appendChild(category);

    card.appendChild(seatText);

    card.appendChild(registerBtn);

    card.appendChild(cancelBtn);

    container.appendChild(card);

});
function displayEvents(events){

    const container =
    document.querySelector("#eventContainer");

    container.innerHTML = "";

    events.forEach(function(event){

        const card =
        document.createElement("div");

        card.classList.add("eventBox");

        card.innerHTML = `

            <h3>${event.name}</h3>

            <p>Category:
            ${event.category}</p>

            <p>Seats:
            ${event.seats}</p>

            <button>
                Register
            </button>

        `;

        const registerBtn =
        card.querySelector("button");

        registerBtn.onclick = function(){

            alert(
                `Registered for ${event.name}`
            );

        };

        container.appendChild(card);

    });

}

displayEvents(
    communityEvents
);

document
.querySelector("#categoryFilter")
.onchange = function(){

    const selectedCategory =
    this.value;

    if(selectedCategory === "All"){

        displayEvents(
            communityEvents
        );

    }
    else{

        const filteredEvents =

        communityEvents.filter(

            event =>
            event.category ===
            selectedCategory

        );

        displayEvents(
            filteredEvents
        );

    }

};

document
.querySelector("#searchBox")
.addEventListener(

    "keydown",

    function(){

        const searchText =
        this.value.toLowerCase();

        const results =

        communityEvents.filter(

            event =>

            event.name
            .toLowerCase()
            .includes(searchText)

        );

        displayEvents(results);

    }

);