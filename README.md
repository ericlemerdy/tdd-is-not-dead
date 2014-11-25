### tdd-is-not-dead

#### Getting started: reproduce the rails application

1. Create an image:

        $ docker build -t rails-tdd .

1. If from Scratch generate application files:

        $ docker run --rm -i --name rails-start -v $(pwd):/usr/src/app -w /usr/src/app -p 3000:3000 rails-tdd /bin/sh
        $ rails new ruby-app
        $ cd ruby-app
        $ rails generate scaffold Employees name:string email:string
        $ exit

1. Install application and create database schema:

        $ docker run --rm -i --name rails-start -v $(pwd):/usr/src/app -w /usr/src/app/ruby-app -p 3000:3000 rails-tdd /bin/sh
        $ bundle install
        $ rake db:migrate

1. Save this state on another command line:

        $ # identify id of the container:
        $ docker ps
        $ # tag the container as a new version of the image:
        $ docker commit <containerId> rails-tdd:bundle-installed

1. Go back to your docker app:

        $ exit

1. Run the rails application:

        $ docker run -d --name rails-server -v $(pwd):/usr/src/app -w /usr/src/app/ruby-app -p 3000:3000 rails-tdd:bundle-installed /usr/local/bundle/bin/rails server


## TODO

Feedback BBL Crafts:
 - Détailler les arguments dans l'historique de la polémique (Qui sont-ils ? Que disent-ils ?)
 - Agenda : on comprend pas trop
 - Jugement des arguments (tampon, on valide !)
 - Faire participer la salle !
