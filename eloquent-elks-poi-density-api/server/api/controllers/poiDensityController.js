import PoiDensityService from '../services/poiDensity.service';

export class POIDensityController {
  async byType(req, res) {
    PoiDensityService.byType(req.query.attractionType).then((r) => {
      if (r) res.json(r);
      else res.status(418).send({"content": "Something went wrong here that shouldn't have. Thus I am a Teapot now"}).end();
    });
  }
}
export default new POIDensityController();

