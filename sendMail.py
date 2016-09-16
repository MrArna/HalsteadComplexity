import sys, getopt
import smtplib
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText
 

fromaddr = "marco.arnaboldi91@gmail.com"
toaddr = "marco.arnaboldi91@gmail.com"
msg = MIMEMultipart()
msg['From'] = fromaddr
msg['To'] = toaddr
msg['Subject'] = "SUBJECT OF THE MAIL"

 
body = "YOUR MESSAGE HERE"

try:
  opts, args = getopt.getopt(sys.argv[1:],"ht:s:b:",["toAddr=","subject=","body="])
except getopt.GetoptError:
  print 'sendMail.py [-t <toaddr>] [-s <subject>] [-b <body>]'
  sys.exit(2)
for opt, arg in opts:
  if opt == '-h':
     print 'sendMail.py [-t <toaddr>] [-s <subject>] [-b <body>]'
     sys.exit()
  elif opt in ("-t", "--toAddr"):
     msg['To'] = arg
  elif opt in ("-s", "--subject"):
     msg['Subject'] = arg
  elif opt in ("-b", "--body"):
     body = arg




msg.attach(MIMEText(body, 'plain'))
 
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()
server.login(fromaddr, "hh7-JAL-mJX-wBB")
text = msg.as_string()
server.sendmail(fromaddr, toaddr, text)
server.quit()