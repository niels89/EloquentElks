FROM mongo
COPY . .
COPY poi.json /poi.json
RUN ["chmod", "+x", "./scripts/init.sh"]
CMD ./scripts/init.sh