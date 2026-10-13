package s2b;

public class Empleado 
{
	/*public static void main(String[] args) 
	{
		System.out.println("");
	}*/
	
	public enum TipoEmpleado{Vendedor, Encargado};
	
	public static float CalculaNominaEmpleado(TipoEmpleado tipo, float ventaMes, float horasExtra)
	{
		float sueldo = 0;
		
		//calculo del sueldo base
		if(tipo == TipoEmpleado.Encargado)
		{
			sueldo = 2000;
		}
		
		else if (tipo == TipoEmpleado.Vendedor)
		{
			sueldo = 2500;
		}
		
		else return -1;
		
		//calculo ventas del mes
		if(ventaMes >= 1500) sueldo += 200;
		else if (ventaMes >= 1000) sueldo += 100;
		else return -1;
		
		//horas extra
		if(horasExtra >= 0)
		{
			sueldo = sueldo + 30 * horasExtra;
		}
		else return -1;
		
		return sueldo;
	}
	
	
	public static float calculoNominaNeta(float nominaBruta)
	{
		float retencion = 0f;
		
		//calculo de la retencion
		if(nominaBruta > 2500)
			retencion = 0.18f;
		
		else if (nominaBruta > 2100)
			retencion = 0.15f;
		
		else if (nominaBruta < 0) return -1;
		
		return nominaBruta * (1 - retencion);
	}
}






























