FROM anapsix/alpine-java:8

ENV nlWebPort 443
ENV nlWebHost neoload-rest.saas.neotys.com
ENV nlWebSSL true
ENV serverPort 9090
ENV nlWebToken a8e8f0c5a4f90c02bfddcb6881e7f6811da26864879a7bd6

RUN apk add --update --no-cache curl \
  && curl --create-dirs -sSLo webhooks-server.jar https://s3-eu-west-1.amazonaws.com/neotys-cpv/webhook-server.jar

EXPOSE ${serverPort}

CMD java -DNL_WEB_HOST=${nlWebHost} -DNL_WEB_PORT=${nlWebPort} -DNL_WEB_USE_SSL=${nlWebSSL} -DNL_WEB_TOKEN=${nlWebToken} -DSERVER_PORT=${serverPort} -jar webhooks-server.jar
