# batchKatas

Sample Labs demostrating spring batch

Spring batch architecture : 
![image](https://github.com/user-attachments/assets/22379d38-f321-4b7d-bd1f-49ebfa680931)
The jobLauncher           = launches the job
The jobRepository         = a group of table that stores persistent data related to our batch (steps) executions
The job                   = a container of steps - it provides general configurations for those steps common properties like : restartability...
The jobInstance           = a job's run (for example 1 of eachMonths) - can have multiple jobExecutions
The jobbParameters        =  holds parameters used to launch a job for a jobInstance
Each step contains an reader - processor - writer

Job 1->* steps 1->1 stepExecution

ExecutionContext = key value pair to allow storing persistent data for a stepExecution/jobExecution
