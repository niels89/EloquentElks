const sonarScanner = require("sonarqube-scanner");

sonarScanner(
    {
        serverUrl: "https://sonarcloud.io",
        options:{
            "sonar.projectKey": "eloquent-elks_EloquentElks_Frontend",
            "sonar.organization": "eloquent-elks",
            "sonar.sources": "src",
            "sonar.tests": "src",
            "sonar.typescript.lcov.reportPaths": "coverage/lcov.info"
        }
    }
)