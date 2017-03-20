package auton;

import subsystems.DriveTrain;
import subsystems.NTHandler;

public class MiddleGearVision extends GearFrame
{
	private int caseSelector = 1;
	
	public void update(DriveTrain dt, NTHandler nettab)
	{
		int distance = nettab.getDistance();
		int mode = nettab.getMode();
		
		switch(caseSelector)
		{
		case 1:
			System.out.println("Driving forward - middle");
			dt.mecanumDrive(0.8, 0, 0);
			
			if(nettab.getZone() == 1 || nettab.getZone() == 2 || nettab.getZone() == 3)
				caseSelector++;
			break;
		case 2:
			System.out.println("Driving to peg - coarse");
			if(nettab.getZone() == 1)
				dt.mecanumDrive(0.3, 20, .2);
			else if(nettab.getZone() == 2)
				dt.mecanumDrive(0.3, 0, 0);
			else if(nettab.getZone() == 3)
				dt.mecanumDrive(0.3, 340, -.2);
			
			if(mode == 2)
				caseSelector++;
			
			break;
		case 3:
			System.out.println("Driving to peg - fine");
			if(nettab.getZone() == 1)
				dt.mecanumDrive(0.3, 20, 0);
			else if(nettab.getZone() == 2)
				dt.mecanumDrive(0.3, 0, 0);
			else if(nettab.getZone() == 1)
				dt.mecanumDrive(0.3, 340, 0);
			
			if(distance < 16)
				caseSelector++;
			
			break;
		case 4:
			dt.mecanumDrive(0, 0, 0);
			System.out.println("Done!");
			break;
		}
	}
}
