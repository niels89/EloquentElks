import middleware from 'swagger-express-middleware';
import * as path from 'path';
import errorHandler from '../api/middlewares/error.handler';

export default function swagger(app, routes) {
  return new Promise((resolve, reject) => {
    middleware(path.join(__dirname, 'api.yml'), app, (err, mw) => {
      if (err) {
        return reject(err);
      }
      // Enable Express' case-sensitive and strict options
      // (so "/entities", "/Entities", and "/Entities/" are all different)
      app.enable('case sensitive routing');
      app.enable('strict routing');

      app.use(mw.metadata());
      app.use(
        mw.files(
          {
            // Override the Express App's case-sensitive
            // and strict-routing settings for the Files middleware.
            caseSensitive: false,
            strict: false,
          },
          {
            useBasePath: false,
            apiPath: '/api/v1/spec'
            // Disable serving the "api.yml" file
            // rawFilesPath: false
          }
        )
      );


      // These two middleware don't have any options (yet)
      app.use(mw.CORS(), mw.validateRequest());

      routes(app);

      // eslint-disable-next-line no-unused-vars, no-shadow
      app.use(errorHandler);
      return resolve();
    });
  });
}
