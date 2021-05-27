import httpMocks from "node-mocks-http";
import {PoiDensityService} from "../server/api/services/poiDensity.service.js";
import * as turf from "@turf/turf";

function getPoisMock(attractionType) {
    return [{latitude: 1, longitude: 1}]
}

let testPolygon = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Polygon",
                "coordinates": [
                    [
                        [
                            8.509254455566406,
                            47.36092044849974
                        ],
                        [
                            8.570365905761719,
                            47.36092044849974
                        ],
                        [
                            8.570365905761719,
                            47.39834920035926
                        ],
                        [
                            8.509254455566406,
                            47.39834920035926
                        ],
                        [
                            8.509254455566406,
                            47.36092044849974
                        ]
                    ]
                ]
            }
        }
    ]
}

let testPoints = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.54736328125,
                    47.382311560261506
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.585472106933594,
                    47.39834920035926
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.528823852539062,
                    47.38998234483188
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.50238800048828,
                    47.38300895044067
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.556632995605469,
                    47.35440838795511
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    8.534317016601562,
                    47.37440716013057
                ]
            }
        }
    ]
}

let randTestPoints = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -75.76171875,
                    39.639537564366684
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -52.3828125,
                    4.915832801313164
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    40.42968749999999,
                    5.61598581915534
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    21.4453125,
                    66.37275500247455
                ]
            }
        },
        {
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": [
                    140.9765625,
                    38.8225909761771
                ]
            }
        }
    ]
}

let testPois = [
    {
      "name": "Gina Mexicana",
      "type": "restaurant",
      "longitude": -73.9667985,
      "latitude": 40.763388799999866,
      "distance": 0.0
    },
    {
      "name": "Pick a Bagel",
      "type": "cafe",
      "longitude": -73.9670304,
      "latitude": 40.76357149999984,
      "distance": 0.0
    }
]


describe('poiDensityService test', () => {
    test('countPointInPolygons: Should count three points in Square', () => {
        // arrange
        let service = new PoiDensityService(getPoisMock("Test"));

        // act
        let pointInPoly = service.countPointInPolygons(testPolygon, testPoints, "poiCount")

        // assert
        expect(pointInPoly.features[0].properties.poiCount).toBe(3)
    })
    test("countPointInPolygons: Should count zero points in square and thus return no features", () => {
        // arrange
        let service = new PoiDensityService(getPoisMock("Test"));

        // act
        let pointInPoly = service.countPointInPolygons(testPolygon, randTestPoints, "poiCount")

        // assert
        expect(pointInPoly.features).toStrictEqual([])
    })
    test("createPointList: Should convert the POIs to features", () => {
        // arrange
        let service = new PoiDensityService(getPoisMock("Test"));
        
        // act
        let pois = service.createPointList(testPois)

        // assert
        expect(pois.length).toBe(2)
        expect(pois[0].type).toStrictEqual("Feature")
        expect(pois[1].type).toStrictEqual("Feature")
    })
    test("mapPoisToFeatureCollection: Should convert the POIs to a FeatureCollection", () => {
        // arrange
        let service = new PoiDensityService(getPoisMock("Test"));

        // act
        let featureCollection = service.mapPoisToFeatureCollection(testPois)
        
        // assert
        expect(featureCollection.features.length).toBe(2)
        expect(featureCollection.type).toStrictEqual("FeatureCollection")
    })
});





