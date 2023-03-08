#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#ifdef _WIN32
#include <Windows.h>
void sen(int t) {	
	Sleep(t);
}
#else
#include <unistd.h>
void sen(int t) {
	if (t == 200) usleep(200*1000);
	else sleep(1);
}
#endif
void start(char *ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(500);
}

//funkcje wypisujące przejscia 
void q2q3(char* ciag);
void q3q2(char* ciag);
void q2q0(char* ciag);
void q2q0(char* ciag);
void q0q2(char* ciag);
void q3q1(char* ciag);
void q1q3(char* ciag);
void q1q0(char* ciag);
void q0q1(char* ciag);

int main(int argc, char *argv[]) {
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n\n");

	char ciag[100] = { 0 };

	//rozne formy wczytywania danych 
	if (argc > 2) {
		printf("<argv[0]>: zly format, prawidłowy format ciagu symboli wejsciowych np \"101101101\"\n");
		return EXIT_FAILURE;
	}
	else if (argc == 1) {
		printf("Podaj ciag symboli wejsciowych(np. 1001101): ");
		fgets(ciag,100,stdin);
	}
	else
		strcpy(ciag, argv[1]);

	printf("\nciag symboli wejsciowych: %s\n\n", ciag);

	int n = strlen(ciag);
	int i;
	int sta = 0;
	start(ciag);
	//petla ryzsujaca przejscia na diagramie
	for (i = 0; i < n; i++) {
		if ((sta==0) && (ciag[i] == '1')) {
			q2q3(ciag);
		
			sta = 3;
		}
		else if ((sta == 3) && (ciag[i] == '1')) {
			q3q2(ciag);
		
			sta = 0;
		}
		else if ((sta == 0) && (ciag[i] == '0')) {
			q2q0(ciag);
		
			sta = 2;
		}
		else if ((sta == 2) && (ciag[i] == '0')) {
			q0q2(ciag);
	
			sta = 0;
		}
		else if ((sta == 3) && (ciag[i] == '0')) {
			q3q1(ciag);
			sta = 1;
		}
		else if ((sta == 1) && (ciag[i] == '0')) {
			q1q3(ciag);
			sta = 0;
		}
		else if ((sta == 1) && (ciag[i] == '1')) {
			q1q0(ciag);
			sta = 2;
		}
		else if ((sta == 2) && (ciag[i] == '1')) {
			q0q1(ciag);
			sta = 1;
		}
		else{
			if (system("CLS")) system("clear");
			printf("zly symbol wejscia w ciagu\n");
			return EXIT_FAILURE;
		}
	}

	if (sta == 0) printf("ciag zostal zaakceptowany");
	else printf("ciag nie zostal zaakceptowany");

	printf("\n\nWszystkie przejscia:\n");
	printf("q2");
	//petla wypisujaca przejscia
	sta = 0;
	for (i = 0; i < n; i++) {
		if ((sta == 0) && (ciag[i] == '1')) {
			printf("-(1)->q3");
			sta = 3;
		
		}
		else if ((sta == 3) && (ciag[i] == '1')) {
			printf("-(1)->q2");
			sta = 0;
		}
		else if ((sta == 0) && (ciag[i] == '0')) {
			printf("-(0)->q0");
			sta = 2;
		}
		else if ((sta == 2) && (ciag[i] == '0')) {
			printf("-(0)->q2");
			sta = 0;
		}
		else if ((sta == 3) && (ciag[i] == '0')) {
			printf("-(0)->q1");
			sta = 1;
		}
		else if ((sta == 1) && (ciag[i] == '0')) {
			printf("-(0)->q3");
			sta = 3;
		}
		else if ((sta == 1) && (ciag[i] == '1')) {
			printf("-(1)->q0");
			sta = 2;
		}
		else if ((sta == 2) && (ciag[i] == '1')) {
			printf("-(1)->q1");
			sta = 1;
		}
	}
	printf("\n");

	return 0;
}

void q2q3(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====                    \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(300);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----              \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1             \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1----         \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");

}
void q3q2(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                                ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                          ----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                         1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                     ----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");


	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->    \n");
	printf("START-->     | q2 |              q3  \n");
	printf("              ====  <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");


}
void q2q0(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("                 |              |  |  \n");
	printf("                 0              0  0  \n");
	printf("                 |              |  |  \n");
	printf("                                v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("                 0              0  0  \n");
	printf("                 |              |  |  \n");
	printf("                                v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("                 |              |  |  \n");
	printf("                                v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("                                v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->        \n");
	printf("START-->       q2                q3   \n");
	printf("                    <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1---->        \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
}
void q0q2(char* ciag) {
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->        \n");
	printf("START-->       q2                q3   \n");
	printf("                    <----1----        \n");
	printf("                                   ^  \n");
	printf("              |                 |  |  \n");
	printf("              0                 0  0  \n");
	printf("              |                 |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1---->        \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->        \n");
	printf("START-->       q2                q3   \n");
	printf("                    <----1----        \n");
	printf("                                   ^  \n");
	printf("              |                 |  |  \n");
	printf("              0                 0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1---->        \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);

	printf("                    ----1---->        \n");
	printf("START-->       q2                q3   \n");
	printf("                    <----1----        \n");
	printf("                                   ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1---->        \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("              ====  ----1---->        \n");
	printf("START-->     | q2 |              q3   \n");
	printf("              ====  <----1----        \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
}
void q3q1(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |                 |  \n");
	printf("              0  0                 0  \n");
	printf("              |  |                 |  \n");
	printf("              v                       \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0                 0  \n");
	printf("              |  |                 |  \n");
	printf("              v                       \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |                 |  \n");
	printf("              v                       \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                       \n");
	printf("                    ----1---->        \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");
}
void q1q3(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                   \n");
	printf("              |  |              |    \n");
	printf("              0  0              0    \n");
	printf("              |  |              |    \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                   \n");
	printf("              |  |              |    \n");
	printf("              0  0              0    \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v    \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                   \n");
	printf("              |  |              |    \n");
	printf("              0  0              0  0 \n");
	printf("              |  |              |  | \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                   \n");
	printf("              |  |              |  | \n");
	printf("              0  0              0  0 \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->  ====  \n");
	printf("START-->       q2              | q3 | \n");
	printf("                    <----1----  ====  \n");
	printf("                 ^                 ^ \n");
	printf("              |  |              |  | \n");
	printf("              0  0              0  0 \n");
	printf("              |  |              |  | \n");
	printf("              v                 v     \n");
	printf("                    ----1---->       \n");
	printf("               q0                q1   \n");
	printf("                    <----1----        \n");
}
void q1q0(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                                ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                          ----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                         1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                         1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ==== \n");
	printf("               q0              | q1 |  \n");
	printf("                     ----1----  ====      \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1---->    \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");	
}
void q0q1(char* ciag) {
	if (system("CLS")) system("clear");
	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====                    \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----    \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("              ====  ----1----    \n");
	printf("             | q0 |              q1   \n");
	printf("              ====  <----1----        \n");
	sen(200);
	if (system("CLS")) system("clear");

	printf("SYMULACJA AUTOMATU SKONCZONEGO M=(Q,E,e,q0,F)\n");
	printf("gdzie Q={q0,q1,q2,q3},E={0,1},F={q2}, q0=q2\n");
	printf("ciag symboli wejsciowych: %s\n\n", ciag);
	printf("                    ----1---->    \n");
	printf("START-->       q2                q3  \n");
	printf("                    <----1----    \n");
	printf("                 ^                 ^  \n");
	printf("              |  |              |  |  \n");
	printf("              0  0              0  0  \n");
	printf("              |  |              |  |  \n");
	printf("              v                 v     \n");
	printf("                    ----1---->  ====  \n");
	printf("               q0              | q1 |  \n");
	printf("                    <----1----  ====      \n");

}
