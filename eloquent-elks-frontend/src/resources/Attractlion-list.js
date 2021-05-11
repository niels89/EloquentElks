import {Cafeteria, Restaurant, Brush, Bar, Book, Java, Anchor, Spa, Bus, Music, Gallery, Cart, IceCream, Car, Shop, Ticket, Bike } from "grommet-icons"
import React from "react";

const labelBar = {label : <Bar />, key:"Bar-logo", id:"Bar-id",value:"bar",a11yTitle:"bar"}
const labelCafeteria = {label: <Cafeteria/>, key:"Cafeteria-logo",id:"Cafe-id",value:"restaurant"}
const labelLibrary = {label: <Book/>, key:"Book-logo", id:"book-id",value:"library"}
const labelCar = {label: <Car/>, key:"Car-logo", id:"car-id",value:"car rental"}
const labelTicket = {label: <Ticket/>, key:"Ticket-logo", id:"ticket-id",value:"ticket rental"}
const labelBike = {label: <Bike/>, key:"Bike-logo", id:"bike-id",value:"bike rental"}
const labelArt = {label: <Brush/>, key:"art-logo", id:"art-id",value:"art centre"}
const labelAnchor = {label: <Anchor/>, key:"boat-logo", id:"boat-id",value:"boat rental"}
const labelBakery = {label: <Restaurant/>, key:"bakery-logo", id:"bakery-id",value:"bakery rental"}
const labelBeauty = {label: <Spa/>, key:"beauty-logo", id:"beauty-id",value:"Beauty"}
const labelBus = {label: <Bus/>, key:"bus-logo", id:"bus-id",value:"bus station"}
const labelCafe = {label: <Java/>, key:"Cafe-logo", id:"cafe-id",value:"cafe"}
const labelCloth = {label: <Shop/>, key:"cloth-logo", id:"cloth-id",value:"clothing store"}
const labelMusic = {label: <Music/>, key:"music-logo", id:"cloth-id",value:"music venue"}
const labelCart = {label: <Cart/>, key:"store-logo", id:"store-id",value:"store"}
const labelIceCream = {label: <IceCream/>, key:"icecream-logo", id:"icecream-id",value:"ice cream"}




export const attractionTypesList = [labelBar,labelCafeteria,labelLibrary, labelCar,labelTicket, labelBike, labelAnchor,
    labelArt, labelBakery, labelBeauty, labelBus, labelCart, labelCafe, labelCloth, labelMusic, labelIceCream]

