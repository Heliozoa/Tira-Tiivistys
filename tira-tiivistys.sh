echo "Haluatko pakata vai avata tiedoston?"
select pa in "Pakata" "Avata"; do
    case $pa in
        Pakata ) 
            read -p "Minkä tiedoston haluat pakata? " t
            read -p "Minne haluat pakata tiedoston? Tiedostoa ei saa olla jo valmiiksi olemassa. (Tyhjä: $t.pt) " pt
            if [ ! -z "$pt" ]
                then
                    echo "Pakataan tiedosto $t tiedostoon $pt"
                    java -jar tira-tiivistys.jar -p "$t" "$pt"
                else
                    echo "Pakataan tiedosto $t tiedostoon $t.pt"
                    java -jar tira-tiivistys.jar -p "$t"
            fi
            ;;
        Avata ) 
            read -p "Minkä tiedoston haluat avata? " t
            read -p "Minne haluat avata tiedoston? Tiedostoa ei saa olla jo valmiiksi olemassa. (Tyhjä: $t.at) " at
            if [ ! -z "$at" ]
                then
                    echo "Pakataan tiedosto $t tiedostoon $at"
                    java -jar tira-tiivistys.jar -a "$t" "$at"
                else
                    echo "Pakataan tiedosto $t tiedostoon $t.at"
                    java -jar tira-tiivistys.jar -a "$t"
            fi
            ;;
    esac
    echo "Valmista."
    exit
done