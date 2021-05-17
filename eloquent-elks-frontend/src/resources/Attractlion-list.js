import {Cafeteria, Restaurant, Brush, Bar, Book, Java, Anchor, Spa, Bus, Music, Gallery, Cart, IceCream, Car, Shop, Ticket, Bike } from "grommet-icons"
import React from "react";

const labelBar = {label : [<Bar />, " Bar"], key:"Bar-logo", id:"Bar-id",value:"bar",a11yTitle:"bar"}
const labelCafeteria = {label: [<Cafeteria/>, "\t Restaurant"], key:"Cafeteria-logo",id:"Cafe-id",value:"restaurant"}
const labelLibrary = {label: [<Book/>, "Library"], key:"Book-logo", id:"book-id",value:"library"}
const labelCar = {label: [<Car/>, "Car Rental"], key:"Car-logo", id:"car-id",value:"car rental"}
const labelTicket = {label: [<Ticket/>, "Cinema"], key:"Ticket-logo", id:"ticket-id",value:"cinema"}
const labelBike = {label: [<Bike/>, "Bike Rental"], key:"Bike-logo", id:"bike-id",value:"bike rental"}
const labelArt = {label: [<Brush/>,"Art Centre"], key:"art-logo", id:"art-id",value:"art centre"}
const labelAnchor = {label: [<Anchor/>,"Boat Rental"], key:"boat-logo", id:"boat-id",value:"boat rental"}
const labelBakery = {label: [<Restaurant/>,"Bakery"], key:"bakery-logo", id:"bakery-id",value:"bakery"}
const labelBeauty = {label: [<Spa/>, "Beauty"], key:"beauty-logo", id:"beauty-id",value:"Beauty"}
const labelBus = {label: [<Bus/>,"Bus Station"], key:"bus-logo", id:"bus-id",value:"bus station"}
const labelCafe = {label: [<Java/>, "Cafe"], key:"Cafe-logo", id:"cafe-id",value:"cafe"}
const labelCloth = {label: [<Shop/>,"Clothing Store"], key:"cloth-logo", id:"cloth-id",value:"clothing store"}
const labelMusic = {label: [<Music/>, "Music Venue"], key:"music-logo", id:"cloth-id",value:"music venue"}
const labelCart = {label: [<Cart/>,"Store"], key:"store-logo", id:"store-id",value:"store"}
const labelIceCream = {label: [<IceCream/>,"Ice Cream Parlor"], key:"icecream-logo", id:"icecream-id",value:"ice cream"}




export const attractionTypesList = [labelBar,labelCafeteria,labelLibrary, labelCar,labelTicket, labelBike, labelAnchor,
    labelArt, labelBakery, labelBeauty, labelBus, labelCart, labelCafe, labelCloth, labelMusic, labelIceCream]

