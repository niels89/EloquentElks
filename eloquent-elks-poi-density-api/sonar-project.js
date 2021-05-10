const sonarScanner = require('sonarqube-scanner');

sonarScanner({
  serverUrl: 'https://sonarcloud.io',
  token: process.env.SONAR_TOKEN,
  options: {
    'sonar.projectKey': 'eloquent-elks_EloquentElks_Frontend',
    'sonar.organization': 'eloquent-elks',
    'sonar.sources': 'server',
    'sonar.tests': 'test',
    'sonar.test.inclusions': 'test/*.test.js,test/**/*.test.js',
    'sonar.typescript.lcov.reportPaths': 'coverage/lcov.info',
  },
});
