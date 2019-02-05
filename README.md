# ABOUT

2019 January
First of all, no doubt, there are a lot of solutions, in this case this is the mine! :) 


# PROBLEM
 You are planning a conference or Event and you have received a lot of proposals choosen after differents filters or process and now you've to fit them into the time constraints of the day. There are a lot of different combinations, so I suggest to write some code lines :)
  
  Some rules and constraints always are funny...
* The conference has different tracks each of which has a morning and afternoon session.
* The morning session and the evening session contains multiple talks.
* Morning sessions start at 9am and must finish by 12 noon, for lunch.
* The lunch takes 60 minutes
* Afternoon sessions begin at 1pm and must finish in time for take some beers with the mates.
* The beers have to start no earlier than 4:00 and no later than 5:00.
* The talk titles can't have numbers in it.
* The talk lenght are either in minutes (not hours) or FASTTALK (5 minutes).
* Talks will start and end ON TIME. There no need gaps between sessions.

Example Test input (Talk names from Big Data Spain 2018):
-Advancing Human Exploration 60min
-MLflow: Accelerating the Machine Learning Lifecycle 45min
-Deep Learning and the technology behind Self-Driving Cars
s 30min
-AI Adoption in Enterprise 45min
-An image is worth a thousand words 45min
-Data Lakes for Financial Entities FASTTALK
-Probabilistic structures for scalable computing 60min
-Three Advanced Analytics use cases in the travel industry 45min
-AI beyond chatbots 30min
-From BigData to AI 30min
-Big Data Governance challenges 45min
-Machine learning for closets 60min
-Big Data in flight test 60min
-Man shall not live by data science alone 45min
-Driving in dataland 30min
-Energizing the Data: building our Data Lane 30min
-Powering up financial institutions with distributed systems 60min
-Man shall not live by data science alone 30min
-Apache Kylin and Use Cases 30min
 
Test output: 
Track 1:
09:00 a. m. Advancing Human Exploration 60min
10:00 a. m. Probabilistic structures for scalable computing 60min
11:00 a. m. Machine learning for closets 60min
12:00 p. m. Lunch 60min
13:00 p. m. An image is worth a thousand words 45min
13:45 p. m. Three Advanced Analytics use cases in the travel industry 45min
14:30 p. m. Big Data Governance challenges 45min
15:15 p. m. Man shall not live by data science alone 45min
16:00 p. m. Apache Kylin and Use Cases 30min
16:30 p. m. Data Lakes for Financial Entities 5min
16:35 p. m. Networking Event

Track 2:
09:00 a. m. Big Data in flight test 60min
10:00 a. m. Powering up financial institutions with distributed systems 60min
11:00 a. m. MLflow: Accelerating the Machine Learning Lifecycle 45min
11:45 a. m. AI Adoption in Enterprise 45min
12:30 p. m. Lunch 60min
13:30 p. m. Deep Learning and the technology behind Self-Driving Cars 30min
14:00 p. m. AI beyond chatbots 30min
14:30 p. m. From BigData to AI 30min
15:00 p. m. Driving in dataland 30min
15:30 p. m. Energizing the Data: building our Data Lane 30min
16:00 p. m. Man shall not live by data science alone 30min
16:30 p. m. Networking Event


### Overview

**IDE:** Eclipse
#### **Files:**
**TalksForSchedule:** File that contains the different talks
**MainConferenceManager.java** The main executable of the applications.
**Proposal.java** Is the object class used
**Configuration.java** Class that contains different configuration values
**Package Utils** This package have the different classes with logic used to resolve the problem
* **ProposalManager.java** Class that transform the each line of the file talks, in a proposal object
* **SchedulerOfProposals.java** This class is in charge of orchestrate the whole process
* **FitAndSuffleSessions.java** Class that calculate the differente combinations of the talks in the different sessions
* **AgendaListGenerator.java** This class generates the Outuput




### Installation
Use your favourite IDE
Import project
Choose MainConferenceManager and run as Java application
