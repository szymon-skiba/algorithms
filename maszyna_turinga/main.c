#include <stdio.h>
#include <stdlib.h>


//funkcje i struktury listy za pomoca ktorych przechowywana jest tasma
typedef struct elem {
	int x;
	struct elem* poprz;
	struct elem* nast;
}elem_t, * list_t;

list_t wstaw_na_pocz(list_t head_ref, int new_data)
{
	struct elem* new_node
		= (struct elem*)malloc(sizeof(new_node));

	new_node->x = new_data;

	new_node->nast = head_ref;
	new_node->poprz = NULL;

	if (head_ref != NULL)
		head_ref->poprz = new_node;

	head_ref = new_node;

	return new_node;
}


list_t wstaw_na_koniec(list_t head_ref, int new_data)
{
	struct elem* new_node
		= (struct elem*)malloc(sizeof(struct elem));

	struct elem* last = head_ref;
	new_node->x = new_data;

	new_node->nast = NULL;

	if (head_ref == NULL) {
		new_node->poprz = NULL;
		head_ref = new_node;
		return new_node;
	}

	while (last->nast != NULL)
		last = last->nast;

	last->nast = new_node;

	new_node->poprz = last;

	return head_ref;
}

void wypisz_liste(list_t l, struct elem*ele, int stan) {
	struct elem* iterator = l;
	while (iterator != NULL) {
		if (iterator ==ele) {
			printf("q%d ", stan);
		}
		else {
			if (iterator->x == -1) {
				printf("B ");
			}
			else
				printf("%d ", iterator->x);
		
		}
		iterator = iterator->nast;
	}
	printf("\n");
}



//funkcje obsugujace maszyne turinga
void opis();
int turing(list_t l, int stan, struct elem* ele, struct elem* pop, char dir);



int main(int argc, char *argv[])
{
	int n, m, wynik=0;
	opis();
	if (argc == 1) {
		printf("\npodaj m: ");
		scanf("%d",&m);
		printf("\npodaj n: ");
		scanf("%d", &n);
	}
	else if (argc == 3) {
		m = atoi(argv[1]);
		n = atoi(argv[2]);
		printf("\nm = %d; n = %d\n", m, n);
	}
	else {
		fprintf(stderr, "\nniepoprawne argumnty\n");
		fprintf(stderr, "\npoprawne argumnty m n, gdzie m>0 n>0; m,n naleza do N\n");
		return 1;
	}

	if (m < 0 || n < 0) {
		fprintf(stderr, "\nniepoprawne argumenty\n");
		fprintf(stderr, "\npoprawne argumnty m n, gdzie m>0 n>0; m,n naleza do N\n");
		return 1;
	}
	
	if (m < n) {
		printf("\nm<n\n");
		printf("Wynik roznicy wlasciwej liczb m-n=%d-%d=%d \n", m, n, wynik);
		return 0;
	}

	printf("Wynik roznicy wlasciwej liczb m-n=%d-%d=%d \n", m, n, m - n);
	struct elem* pierwszy = NULL;
	list_t lista = NULL;		
	lista = wstaw_na_koniec(lista, -1);
	for (int i = 0; i < n + m + 1; i++) {
		if (i != m)
			lista=wstaw_na_koniec(lista, 0);
		else
			lista=wstaw_na_koniec(lista, 1);
	}
	lista = wstaw_na_koniec(lista, -1);
	pierwszy = lista;

	printf("stan tasmy:\n");
	struct elem* iterator = lista;
	while (iterator != NULL) {	
		if (iterator->x == -1) {
			printf("B ");
		}
		else
			printf("%d ", iterator->x);
		iterator = iterator->nast;
	}
	printf("\n");

	printf("poczatek pracy\n");
	int r=turing(lista, 0, pierwszy->nast, pierwszy->poprz, 'l');	
	printf("zakonczenie pracy\n");

	iterator = lista;
	while (iterator != NULL) {
		if (iterator->x == 0)
			wynik++;
		if (iterator->x == -1) {
			printf("B ");
		}
		else
			printf("%d ", iterator->x);
		iterator = iterator->nast;
	}
	printf("\n");

	if (r == 1) {
		printf("Ciag zostal zaakceptowany\n");
		printf("Wynik roznicy wlasciwej liczb m-n=%d-%d=%d \n", m, n, wynik);
		if (m - n == wynik) printf("Wynik ten jest zgodny z matematycznym opisem roznicy wlasciwej\n");
		else printf("Wynik ten nie jest zgodny z matematycznym opisem roznicy wlasciwej\n");
	}
	else
		printf("\n%d ciag nie zaakceptowany\n",wynik);


	return 0;


}


void opis()
{
	printf(" Emulator maszyny Turinga obliczajacy roznice wlasciwa\n");

	printf(" Roznica wlasciwa: \n");
	printf("             | m - n dla m >= n\n");
	printf("     m - n = |               \n");
	printf("             | 0     dla m < n \n\n");

	printf(" Postac maszyny Turinga:\n");
	printf("     M = ({q0, q1, q2, q3, q4, a5, a6}, {0, 1}, {0, 1, B}, d, q0, B, 0)\n\n"); 

	printf(" Tablica przejsc:\n");
	printf("     ==========================================\n");
	printf("     |   d   |     0    |     1    |     B    |\n");
	printf("     ==========================================\n");
	printf("     |   q0  | (q1,B,P) | (q5,B,P) |     -    |\n");
	printf("     |   q1  | (q1,O,P) | (q2,1,P) |     -    |\n");
	printf("     |   q2  | (q3,1,L) | (q2,1,P) | (q4,B,L) |\n");
	printf("     |   q3  | (q3,0,L) | (q3,1,L) | (q0,B,P) |\n");
	printf("     |   q4  | (q4,0,L) | (q4,B,L) | (q6,0,P) |\n");
	printf("     |   q5  | (q5,B,P) | (q5,B,P) | (q6,B,P) |\n");
	printf("     |   q6  |     -    |     -    |     -    |\n");
	printf("     ==========================================\n");
}


int turing(list_t l, int stan, struct elem* ele, struct elem*pop, char dir) {


	wypisz_liste(l,ele,stan);
	if (stan == 6) {
		return 1;
	}

	else if (stan == 0) {
		if (ele->x == 0) {
			ele->x = -1;
			turing(l, 1, ele->nast,ele, 'p');
		}
		else if (ele->x == 1) {
			ele->x = -1;
			turing(l, 5, ele->nast, ele,'p');
		}
		else if (ele == NULL || ele->x == -1) {
			return 1;
		}
	}

	else if (stan == 1) {
		if (ele->x == 0) {
			ele->x = 0;
			turing(l, 1, ele->nast, ele,'p');
		}
		else if (ele->x == 1) {
			ele->x = 1;
			turing(l, 2, ele->nast, ele,'p');
		}
		else if (ele == NULL || ele->x == -1) {
			return 1;
		}
	}

	else if (stan == 2) {
		if (ele->x == 0) {
			ele->x = 1;
			turing(l, 3, ele->poprz,ele, 'l');
		}
		else if (ele->x == 1) {
			ele->x = 1;
			turing(l, 2, ele->nast,ele, 'p');
		}
		else if (ele == NULL){
			if (dir == 'p') {
				l=wstaw_na_koniec(l, -1);
				pop->nast->x = -1;
				turing(l, 4, pop->nast->poprz, pop->nast, 'l');
			}
			if (dir == 'l') {
				l=wstaw_na_pocz(l, -1);
				pop->poprz->x = -1;
				turing(l, 4, pop->poprz->poprz, pop->poprz, 'l');
			}
		}
		else if (ele->x == -1) {
			ele->x = -1;
			turing(l, 4, ele->poprz, ele, 'l');
		}
	}
	else if (stan == 3) {
		if (ele->x == 0) {
			ele->x = 0;
			turing(l, 3, ele->poprz,ele, 'l');
		}
		else if (ele->x == 1) {
			ele->x = 1;
			turing(l, 3, ele->poprz, ele ,'l');
		}
		else if (ele == NULL) {
			if (dir == 'p') {
				l=wstaw_na_koniec(l, -1);
				pop->nast->x = -1;
				turing(l, 4, pop->nast->nast, pop->nast, 'p');
			}
			if (dir == 'l') {
				l=wstaw_na_pocz(l, -1);
				pop->poprz->x = -1;
				turing(l, 4, pop->poprz->nast, pop->poprz, 'p');
			}
		}
		else if (ele->x == -1) {
			ele->x = -1;
			turing(l, 0, ele->nast, ele, 'p');
		}
	}
	else if (stan == 4) {
		if (ele->x == 0) {
			ele->x = 0;
			turing(l, 4, ele->poprz, ele, 'l');
		}
		else if (ele->x == 1) {
			ele->x = -1;
			turing(l, 4, ele->poprz, ele, 'l');
		}
		else if (ele == NULL) {
			if (dir == 'p') {
				l = wstaw_na_koniec(l, -1);
				pop->nast->x = 0;
				turing(l, 4, pop->nast->nast,pop->nast, 'p');
			}
			if (dir == 'l') {
				l = wstaw_na_pocz(l, -1);
				pop->poprz->x = 0;
				turing(l, 4, pop->poprz->nast,pop->poprz, 'p');
			}
		}
		else if (ele->x == -1) {
			ele->x = 0;
			turing(l, 6, ele->nast,ele, 'p');
		}
	}

	else if (stan == 5) {
		if (ele->x == 0) {
			ele->x = -1;
			turing(l, 5, ele->nast, ele, 'p');
		}
		else if (ele->x == 1) {
			ele->x = -1;
			turing(l, 5, ele->nast, ele, 'p');
		}
		else if (ele == NULL) {
			if (dir == 'p') {
				l = wstaw_na_koniec(l, -1);
				pop->nast->x = -1;
				turing(l, 6, pop->nast->nast, pop->nast, 'p');
			}
			if (dir == 'l') {
				l = wstaw_na_pocz(l, -1);
				pop->poprz->x = -1;
				turing(l, 6, pop->poprz->nast, pop->poprz, 'p');
			}
		}
		else if (ele->x == -1) {
			turing(l, 4, ele->poprz,ele, 'l');
		}
	}
	else 
		return 0;
}




