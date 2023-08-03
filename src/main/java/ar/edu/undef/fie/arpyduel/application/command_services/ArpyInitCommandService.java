package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.domain.duel_types.types.Friendly;
import ar.edu.undef.fie.arpyduel.domain.duel_types.types.Normal;
import ar.edu.undef.fie.arpyduel.domain.duel_types.types.Ranked;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.enchants.Basic;
import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.enchants.MirrorStrike;
import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Bow;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Staff;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Sword;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Hunter;
import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Warrior;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Mage;
import ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs.Confused;
import ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs.AimDisruption;
import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs.BurnMagic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArpyInitCommandService implements CommandLineRunner {
    private final FindEnchantCommandQuery enchantQuery;
    private final EnchantCommandService enchantService;
    private final FindClassCommandQuery classQuery;
    private final ClassCommandService classService;
    private final FindDebuffCommandQuery debuffQuery;
    private final DebuffCommandService debuffService;

    private final FindWeaponTypeCommandQuery weaponTypeQuery;
    private final WeaponTypeCommandService weaponTypeService;
    private final FindDuelTypeCommandQuery duelTypeQuery;
    private final DuelTypeCommandService duelTypeService;




    public ArpyInitCommandService(FindEnchantCommandQuery enchantQuery, EnchantCommandService enchantService, FindClassCommandQuery classQuery, ClassCommandService classService, FindDebuffCommandQuery debuffQuery, DebuffCommandService debuffService, FindWeaponTypeCommandQuery findArmaTipo, WeaponTypeCommandService weaponTypeService, FindDuelTypeCommandQuery duelTypeQuery, DuelTypeCommandService duelTypeService) {
        this.enchantQuery = enchantQuery;
        this.enchantService = enchantService;
        this.classQuery = classQuery;
        this.classService = classService;
        this.debuffQuery = debuffQuery;
        this.debuffService = debuffService;
        this.weaponTypeQuery = findArmaTipo;
        this.weaponTypeService = weaponTypeService;
        this.duelTypeQuery = duelTypeQuery;
        this.duelTypeService = duelTypeService;
    }


    @Override
    public void run(String... args) throws Exception {
        if (enchantQuery.countEnchants() == 0){
            //Enchanting INIT
            List<Enchant> encantamientosInitList = new ArrayList<Enchant>();
            encantamientosInitList.add(new Basic("Basic", "Basic weapon attack - No extra damage"));
            encantamientosInitList.add(new MirrorStrike("Mirror Strike", "30% chance to deal double damage - 30% chance to deal 1/3 of the weapon damage to self - 40% chance to deal normal damage"));

            enchantService.createAll(encantamientosInitList);
        }

        if (classQuery.countClasses() == 0){
            //Classes INIT
            List<CharacterClass> clasesInitList = new ArrayList<CharacterClass>();
            clasesInitList.add(new Hunter("Hunter"));
            clasesInitList.add(new Warrior("Warrior"));
            clasesInitList.add(new Mage("Mage"));

            classService.createAll(clasesInitList);
        }

        var debuffs = new ArrayList<Debuff>();
        if (debuffQuery.countDebuffs() == 0){
            //Debuff INIT
            debuffs.add(new Confused("Confusion", "25% chance of confusion hit"));
            debuffs.add(new AimDisruption("Aim Disruption", "Aim Disruption"));
            debuffs.add(new BurnMagic("Burn Magic", "Hit opponent with 40% of his spell power"));

            debuffService.createAll(debuffs);
        }


        if (weaponTypeQuery.countWeaponTypes() == 0){

            List<WeaponType> armaTiposInitList = new ArrayList<WeaponType>();
            armaTiposInitList.add(new Bow());
            armaTiposInitList.add(new Sword());
            armaTiposInitList.add(new Staff());

            weaponTypeService.createAll(armaTiposInitList);
        }
        if (duelTypeQuery.countWeaponTypes() == 0){

            List<DuelType> duelTypesInit = new ArrayList<>();
            var availableTypes = DuelEnum.values();
            var ranked = new Ranked(DuelEnum.RANKED);
            var normal = new Normal(DuelEnum.NORMAL);
            var friendly = new Friendly(DuelEnum.FRIENDLY);
            duelTypesInit.add(ranked);
            duelTypesInit.add(normal);
            duelTypesInit.add(friendly);

            duelTypeService.createAll(duelTypesInit);
        }
    }

}
