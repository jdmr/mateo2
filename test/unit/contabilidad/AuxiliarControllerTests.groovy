package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(AuxiliarController)
@Mock(Auxiliar)
class AuxiliarControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/auxiliar/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.auxiliarInstanceList.size() == 0
        assert model.auxiliarInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.auxiliarInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.auxiliarInstance != null
        assert view == '/auxiliar/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/auxiliar/show/1'
        assert controller.flash.message != null
        assert Auxiliar.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/auxiliar/list'


        def auxiliar = new Auxiliar()

        // TODO: populate domain properties

        assert auxiliar.save() != null

        params.id = auxiliar.id

        def model = controller.show()

        assert model.auxiliarInstance == auxiliar
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/auxiliar/list'


        def auxiliar = new Auxiliar()

        // TODO: populate valid domain properties

        assert auxiliar.save() != null

        params.id = auxiliar.id

        def model = controller.edit()

        assert model.auxiliarInstance == auxiliar
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/auxiliar/list'

        response.reset()


        def auxiliar = new Auxiliar()

        // TODO: populate valid domain properties

        assert auxiliar.save() != null

        // test invalid parameters in update
        params.id = auxiliar.id

        controller.update()

        assert view == "/auxiliar/edit"
        assert model.auxiliarInstance != null

        auxiliar.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/auxiliar/show/$auxiliar.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/auxiliar/list'

        response.reset()

        def auxiliar = new Auxiliar()

        // TODO: populate valid domain properties
        assert auxiliar.save() != null
        assert Auxiliar.count() == 1

        params.id = auxiliar.id

        controller.delete()

        assert Auxiliar.count() == 0
        assert Auxiliar.get(auxiliar.id) == null
        assert response.redirectedUrl == '/auxiliar/list'


    }


}