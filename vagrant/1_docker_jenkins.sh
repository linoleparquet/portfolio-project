#!/bin/sh

echo 'START - Configure CI/CD Machine'

export DEBIAN_FRONTEND=noninteractive

echo '[1] Install Docker'
curl -fsSL https://get.docker.com/ | sh &>/dev/null
sudo usermod -aG docker vagrant
sudo apt-get install docker-compose -y &>/dev/null

echo '[2] Deploy Jenkins and Docker registry'
docker-compose -f docker-compose/docker-compose.yaml up --build -d&>/dev/null

echo 'END - Configure CI/CD Machine'
echo 'Jenkins is available at http://192.168.33.10'