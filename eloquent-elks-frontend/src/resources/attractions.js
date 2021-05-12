import {Cafeteria, Restaurant, Brush, Bar, Book, Java, Anchor, Spa, Bus, Music, Cart, IceCream, Car, Shop, Ticket, Bike } from "grommet-icons"
import React from "react";

// TODO: synchronize with ASEP-63, this is only a modified copy of its work in progress commit
export function getAttractionTypes(color, size){
    return [
        { label: <Bar color={color} size={size} />, key: "Bar-logo", id: "Bar-id", value: "bar", a11yTitle: "bar" , caption: "Bar"},
        { label: <Cafeteria color={color} size={size} />, key: "Cafeteria-logo", id: "Cafe-id", value: "restaurant", caption: "Restaurant" },
        { label: <Book color={color} size={size} />, key: "Book-logo", id: "book-id", value: "library", caption: "Library"},
        { label: <Car color={color} size={size} />, key: "Car-logo", id: "car-id", value: "car rental", caption: "Car Rental"},
        { label: <Ticket color={color} size={size} />, key: "Ticket-logo", id: "ticket-id", value: "ticket rental", caption: "Tickets" },
        { label: <Bike color={color} size={size} />, key: "Bike-logo", id: "bike-id", value: "bike rental", caption: "Bike Rental" },
        { label: <Brush color={color} size={size} />, key: "art-logo", id: "art-id", value: "art centre", caption: "Art Centre" },
        { label: <Anchor color={color} size={size} />, key: "boat-logo", id: "boat-id", value: "boat rental", caption: "Boat Rental" },
        { label: <Restaurant color={color} size={size} />, key: "bakery-logo", id: "bakery-id", value: "bakery rental", caption: "Bakery" },
        { label: <Spa color={color} size={size} />, key: "beauty-logo", id: "beauty-id", value: "Beauty", caption: "Beauty"},
        { label: <Bus color={color} size={size} />, key: "bus-logo", id: "bus-id", value: "bus station", caption: "Bus Station"},
        { label: <Java color={color} size={size} />, key: "Cafe-logo", id: "cafe-id", value: "cafe", caption: "Coffee Shop" },
        { label: <Shop color={color} size={size} />, key: "cloth-logo", id: "cloth-id", value: "clothing store", caption: "Clothing" },
        { label: <Music color={color} size={size} />, key: "music-logo", id: "cloth-id", value: "music venue", caption: "Music Store" },
        { label: <Cart color={color} size={size} />, key: "store-logo", id: "store-id", value: "store", caption: "Shopping" },
        { label: <IceCream color={color} size={size} />, key: "icecream-logo", id: "icecream-id", value: "ice cream", caption: "Ice Cream" }
    ]
}