line=1
while [ $line -lt 14753 ]; do
   head -$line dante.txt | tail -1
   sleep 0.1
   : $((line++))
done